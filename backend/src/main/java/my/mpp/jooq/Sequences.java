/*
 * This file is generated by jOOQ.
 */
package my.mpp.jooq;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.customers_id_seq</code>
     */
    public static final Sequence<Integer> CUSTOMERS_ID_SEQ = new SequenceImpl<Integer>("customers_id_seq", JPublic.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.orders_id_seq</code>
     */
    public static final Sequence<Integer> ORDERS_ID_SEQ = new SequenceImpl<Integer>("orders_id_seq", JPublic.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.products_id_seq</code>
     */
    public static final Sequence<Integer> PRODUCTS_ID_SEQ = new SequenceImpl<Integer>("products_id_seq", JPublic.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}
