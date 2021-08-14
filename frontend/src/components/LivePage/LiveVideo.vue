<template>
	<div>

		<!-- test -->
		<div>
		🧡🧡 mySessionId: {{ this.mySessionId }} 🧡🧡<br>
		myUserName: {{ this.myUserName }} - isSeller: {{ isSeller }}<br>
		💛💛 liveInfo: {{ liveInfo }} 💛💛
		</div>

		<div id="session">
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div>
			<div v-if="isSeller" class="inline leave">
				<span class="caution2">방송종료 버튼을 클릭하면 방송이 종료됩니다!</span>
				<button v-if="session" class="btn-r" @click="leaveSession">방송 종료</button>
			</div>
			<div v-else>
				<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/LivePage/UserVideo';
// import { mapActions } from 'vuex';
// const liveStore = 'liveStore'

axios.defaults.headers.post['Content-Type'] = 'application/json';

const BASE_URL = process.env.VUE_APP_BASE_URL

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_URL = "https://" + "i5c103.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
	name: 'App',
	props: {
		liveInfo: Object,
		isSeller: Boolean,
	},
	components: {
		UserVideo,
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			// mySessionId: String(localStorage.getItem('wschat.roomId')),
			// myUserName: JSON.parse(localStorage.getItem('userInfo')).id,
			mySessionId: localStorage.getItem('wschat.roomId') ? String(localStorage.getItem('wschat.roomId')) : null,
			myUserName: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).id : null,
		}
	},
	created() {
		this.joinSession()
	},
	methods: {
		// 세션 가입 -> created (판매자인 경우에만)
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

						// 판매자인 경우에만 mainStreamManager 등록
						if (this.liveInfo.userid === this.myUserName) {
							let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
								// resolution: '640x480',  // The resolution of your video
								resolution: '760x530',  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false       	// Whether to mirror your local video or not
							});
	
							this.mainStreamManager = publisher;
							this.session.publish(this.mainStreamManager);
						}
					})
					.catch(error => {
						console.log('세션 연결 중 에러발생:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) {
				this.session.disconnect();
				const url = BASE_URL + `/live/end/${this.mySessionId}`
				axios.delete(url)
				.then(res => console.log(res))
				.catch(err => console.log(err))
				
			}

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
		// ...mapActions(liveStore, ['getLiveInfo']),
	},


}
</script>

<style scoped>
.caution2 {
	font-size: 1.05rem;
	font-weight: 500;
  color: #ff5f37;
}
.btn-r {
  background-color: rgb(255, 62, 62);
  border-radius: 15px;
  padding: 8px 25px;
	font-size: 1.2rem;
  line-height: 42px;
  color: #fff;
	margin-left: 10px;
}
.btn-r:hover {
	text-shadow: 0 0 5px rgb(255, 238, 0), 0 0 15px rgb(255, 238, 0), 0 0 20px rgb(255, 238, 0), 0 0 30px rgb(255, 238, 0);
  color: #fff;
}
.leave {
	float: right;
}
</style>