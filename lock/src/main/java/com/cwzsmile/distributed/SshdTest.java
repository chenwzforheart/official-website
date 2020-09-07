package com.cwzsmile.distributed;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.util.net.SshdSocketAddress;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author csh9016
 * @date 2020/9/7
 */
public class SshdTest {

    private static final String user = "";
    private static final String host = "";
    private static final int port = 22;
    private static final String password = "";


    private static final int lport = 3305;
    private static final String rhost = "";
    private static final int rport = 3306;

    public static void main(String[] args)throws IOException ,SQLException {

        SshClient client = SshClient.setUpDefaultClient();
        client.start();


        ClientSession session = client.connect(user, host, port).verify().getSession();
        session.addPasswordIdentity(password);
        if(!session.auth().verify().isSuccess()) {
            System.out.println("auth failed");
        }

        System.out.println(session.createLocalPortForwardingTracker(new SshdSocketAddress("127.0.0.1", lport), new SshdSocketAddress(rhost, rport)));

        SshTest.sql();

        client.stop();
    }

}
