package net.hdavid;

import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.hdavid.grpcserver.generated.ApiSimpleGrpc;
import io.hdavid.grpcserver.generated.ApiSimpleGrpc.ApiSimpleBlockingStub;
import io.hdavid.grpcserver.generated.ClientId;
import io.hdavid.grpcserver.generated.ClientInfo;
import io.hdavid.grpcserver.generated.DocumentType;

public class Client {
    public static void main(String[] args) throws Exception {

      ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 8989)
          .usePlaintext().build();
      ApiSimpleBlockingStub bstub = ApiSimpleGrpc.newBlockingStub(channel);
      System.out.println("--");
      ClientId cid = ClientId.newBuilder()
          .setDocumentType(DocumentType.RUC)
          .setDocumentNumber("3239726")
          .build();
      System.out.println(cid);
      ClientInfo clientInfo = bstub.getClientInfo(cid);

      System.out.println("--");
      System.out.println(clientInfo);
      System.out.println("--");

      System.out.println("cidjson: "+JsonFormat.printer().includingDefaultValueFields().print(cid));
      System.out.println("--");
      System.out.println(JsonFormat.printer().print(clientInfo));


    }
}
