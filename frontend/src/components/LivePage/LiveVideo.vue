<template>
	<div>
		<div id="session"> 
			<div id="seller-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div>
			<div v-if="isSeller" class="inline leave">
				<span class="caution2">방송종료 버튼을 클릭하면 방송이 종료됩니다!</span>
				<button v-if="session" class="btn-r" @click="leaveSession">방송 종료</button>
			</div>
			<div v-else>
				<user-video id="buyer-video" :stream-manager="subscribers[0]"/>
			</div>
		</div>
		<div class="viewer-count" v-if="liveInfo">
			<i class="bi bi-person" style="margin-right:10px;font-size:1.6rem;"></i>{{liveInfo.liveviewercount}}
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/LivePage/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const BASE_URL = process.env.VUE_APP_BASE_URL
const OPENVIDU_SERVER_URL = "https://" + "i5c103.p.ssafy.io:8443";
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
			mySessionId: localStorage.getItem('wschat.roomId') ? String(localStorage.getItem('wschat.roomId')) : null,
			myUserName: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')).id : null,
		}
	},
	created() {
		this.joinSession()
	},
	methods: {
		joinSession () {
			this.OV = new OpenVidu();
			this.OV.enableProdMode()

			this.session = this.OV.initSession();

			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
						if (this.liveInfo.userid === this.myUserName) {
							let publisher = this.OV.initPublisher(undefined, {
								audioSource: undefined,
								videoSource: undefined,
								publishAudio: true,
								publishVideo: true,
								resolution: '760x530',
								frameRate: 30,
								insertMode: 'APPEND',
								mirror: false
							});
							this.mainStreamManager = publisher;
							this.session.publish(this.mainStreamManager);
						}
					})
					.catch(error => {
						console.log('세션 연결 중 에러발생:', error.code, error.message);
					});
			});
		},

		leaveSession () {
			if (this.session) {
				this.session.disconnect();
				this.session = undefined;
				this.mainStreamManager = undefined;
				this.publisher = undefined;
				this.subscribers = [];
				this.OV = undefined;
				
				const url = BASE_URL + `/live/end/${this.mySessionId}`
				axios.delete(url)
			}
			window.removeEventListener('beforeunload', this.leaveSession);
		},


		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
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

		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
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
.viewer-count {
	margin-top: 10px;
	display: flex;
	align-items: center;
	font-size:1.3rem;
	margin-left: 10px;
}
</style>