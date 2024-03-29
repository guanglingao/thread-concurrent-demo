package main.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableDemo {

    public static Integer calc(Integer para){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        return para*para;
    }

    public static void main(String[] args)throws InterruptedException, ExecutionException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                .exceptionally(ex->{
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply(i ->Integer.toString(i))
                .thenApply(str -> "\""+str+"\"")
                .thenAccept(System.out::println);

        fu.get();
    }
}
