package io.hdavid.grpcserver;

import io.grpc.stub.StreamObserver;
import io.hdavid.grpcserver.generated.ApiSimpleGrpc.ApiSimpleImplBase;
import io.hdavid.grpcserver.generated.ClientId;
import io.hdavid.grpcserver.generated.ClientInfo;
import io.hdavid.grpcserver.generated.DocumentType;
import io.hdavid.grpcserver.generated.TransferRequest;
import io.hdavid.grpcserver.generated.TransferRequestResp;

public class ApiSimple extends ApiSimpleImplBase {

  @Override
  public void getClientInfo(ClientId request, StreamObserver<ClientInfo> responseObserver) {
    DocumentType dt = request.getDocumentType();
    if (dt == DocumentType.UNRECOGNIZED) {
      responseObserver.onNext(
          ClientInfo.newBuilder()
              .setFullName("No conocido")
              .setPhoneNumber("0000000000")
              .setAddress("")
              .build()
      );
      responseObserver.onCompleted();
    }

    String docNr = request.getDocumentNumber();
    // consultar satelite lunar
    responseObserver.onNext(
        ClientInfo.newBuilder()
            .setFullName("Derlis")
            .setPhoneNumber("+595971110799")
            .setAddress("")
            .build()
    );
    responseObserver.onCompleted();

  }

  @Override
  public void transferMoney(TransferRequest request,
      StreamObserver<TransferRequestResp> responseObserver) {
    super.transferMoney(request, responseObserver);
  }
}
