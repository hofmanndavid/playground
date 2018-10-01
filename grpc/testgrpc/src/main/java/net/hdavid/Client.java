package net.hdavid;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObservers;
import net.hdavid.testrpc.grpcdef.DefResponse;
import net.hdavid.testrpc.grpcdef.MessagePayload;
import net.hdavid.testrpc.grpcdef.ServerMethodsGrpc;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8989")
                .usePlaintext()
                .build();

        MessagePayload mp = MessagePayload.newBuilder()
                .addHobbies("uno")
                .addHobbies("dos")
                .setUser("Davide")
                .setLatitude(112)
                .build();


//        ServerMethodsGrpc.ServerMethodsBlockingStub bstub = ServerMethodsGrpc.newBlockingStub(channel);
//        DefResponse resp = null;
//        resp = bstub.getInfo(mp);
//        System.out.println(resp);



//        ServerMethodsGrpc.ServerMethodsStub stub = ServerMethodsGrpc.newStub(channel);
//        stub.getInfo(mp, new StreamObserver<DefResponse>() {
//            public void onNext(DefResponse defResponse) {
//                System.out.println(defResponse);
//            }
//            public void onError(Throwable throwable) {
//                System.out.println("client error");
//            }
//            public void onCompleted() {
//                System.out.println("client completed");
//            }});

        while (true) {
            try {
                ServerMethodsGrpc.ServerMethodsFutureStub fstub = ServerMethodsGrpc.newFutureStub(channel);
                ListenableFuture<DefResponse> fresp = fstub.getInfo(mp);
                DefResponse resp = fresp.get();
                System.out.println(resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Thread.sleep(2000);
        }


//        channel.awaitTermination(1, TimeUnit.MINUTES);

    }
}
