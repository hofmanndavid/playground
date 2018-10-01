package io.ingenia.crosscut;


import io.ebean.Ebean;
import io.ebean.EbeanServer;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenceBean {


    public interface CodeCallable {
        Object runInTx(EbeanServer ebeanServer);
    }

    public Object ex(CodeCallable c) {

//        EbeanServer des = Ebean.getDefaultServer();
//        return des.execute(
//                TxScope.requiresNew().setRollbackFor(Exception.class),
//                () -> c.runInTx(des)
//        );


        EbeanServer des = null;
        boolean noexception = true;
        try {
            des = Ebean.getDefaultServer();
            des.beginTransaction();

            return c.runInTx(des);
        } catch (Exception e) {
            noexception = false;
            try {
                des.rollbackTransaction();
            } catch (Exception ie) {
                ie.printStackTrace();
            } finally {
                throw new RuntimeException(e);
            }
        } finally {
            if (noexception) {
                try {
                    des.commitTransaction();
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
            try {
                des.endTransaction();
            } catch (Exception ie) {
                ie.printStackTrace();
            }
        }

//        throw new RuntimeException("NPEHH"); // No possible exception happen here


    }

    public interface CodeRunnable {
        void runInTx(EbeanServer ebeanServer);
    }

    public void exf(CodeRunnable c) {
        EbeanServer des = null;
        try {
            des = Ebean.getDefaultServer();
            des.beginTransaction();

            c.runInTx(des);

            des.commitTransaction();
        } catch (Exception e) {
            try {
                des.rollbackTransaction();
            } catch (Exception ie) {
                ie.printStackTrace();
            } finally {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                des.endTransaction();
            } catch (Exception ie) {
                ie.printStackTrace();
            }
        }
//        EbeanServer des = Ebean.getDefaultServer();
//        des.execute(
//                    TxScope.requiresNew().setRollbackFor(Exception.class),
//                    () -> c.runInTx(des)
//            );
    }

}
