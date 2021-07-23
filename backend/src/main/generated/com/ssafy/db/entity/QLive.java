package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLive is a Querydsl query type for Live
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLive extends EntityPathBase<Live>{

    private static final long serialVersionUID = 846542478L;

    public static final QLive live = new QLive("live");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> liveid = _super.id;

    public final StringPath livetitle = createString("livetitle");

    public final StringPath liveds = createString("liveds");

    public final StringPath liveimg = createString("liveimg");

    public final StringPath livewriter = createString("livewriter");

    public final StringPath userid = createString("userid");

    public QLive(String variable) {
        super(Live.class, forVariable(variable));
    }

    public QLive(Path<? extends Live> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLive(PathMetadata metadata) {
        super(Live.class, metadata);
    }

}