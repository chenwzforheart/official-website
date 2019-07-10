package com.cwzsmile.distributed.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;

/**
 * @author csh9016
 * @date 2019/7/10
 */
public class Index {

    public static void main(String[] args) {
        Client client = new NodeClient(null, null);
        //1.增加filed
        //2.索引文档
        //3.查询
    }
}
