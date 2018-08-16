package io.hdavid.grpcserver;

//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import io.grpc.stub.StreamObserver;
//import net.hdavid.testrpc.grpcdef.DefResponse;
//import net.hdavid.testrpc.grpcdef.MessagePayload;
//import net.hdavid.testrpc.grpcdef.ServerMethodsGrpc;
//
//import java.util.Date;
//import net.hdavid.testrpc.grpcdef.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Server server = ServerBuilder.forPort(8989)
                .addService(new ApiSimple())
                .build();

        server.start();
        server.awaitTermination();
    }
//
//    public static class ServerMethodsImpl extends ServerMethodsGrpc.ServerMethodsImplBase {
//        @Override
//        public void getInfo(MessagePayload request, StreamObserver<DefResponse> responseObserver) {
//            System.out.println("incomming: "+ request.getUser());
//
//            responseObserver.onNext(DefResponse.newBuilder()
//                    .setData("hola loco"+ new Date())
//                    .build());
//
//            responseObserver.onCompleted();
//
//        }
//    }

}
