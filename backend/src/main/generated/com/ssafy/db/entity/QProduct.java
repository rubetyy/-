package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 846542479L;

    public static final QProduct product = new QProduct("product");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> product_pk = createNumber("product_pk");

    public final StringPath user_id_seller = createString("user_id_seller");

    public final NumberPath<Long> category_pk = createNumber("category_pk");

    public final StringPath product_title = createString("product_title");

    public final StringPath product_description = createString("product_description");

    public final NumberPath<Long> product_price = createNumber("product_price");

    public final NumberPath<Long> product_view_count = createNumber("product_view_count");

    public final NumberPath<Long> product_is_live = createNumber("product_is_live");

    public final NumberPath<Long> product_is_sold = createNumber("product_is_sold");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

