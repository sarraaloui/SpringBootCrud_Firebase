package com.example.firebase.Service;

import com.example.firebase.Entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String Collection_name="products";

    public String saveProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
       ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(Collection_name).document(product.getName()).set(product);
        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public Product getProductDetails (String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference= dbFirestore.collection(Collection_name).document(name);
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document=future.get();
       if(document.exists()) {
           Product product=document.toObject(Product.class);
           return product;
       }else {
           return null;
       }
    }

    public String update(Product product) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(Collection_name).document(product.getName()).set(product);
        return collectionApiFuture.get().getUpdateTime().toString();


    }


    public String delete (String  name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(Collection_name).document(name).delete();
        return "deleted successfully";


    }


    public List<Product> getAll () throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference= dbFirestore.collection(Collection_name).listDocuments();
        Iterator<DocumentReference> iterator=documentReference.iterator();
        List<Product> productList=new ArrayList<>();
        Product product=null;
        while (iterator.hasNext()){
            DocumentReference documentReference1= iterator.next();
            ApiFuture<DocumentSnapshot> future=documentReference1.get();
            DocumentSnapshot document=future.get();
             product=document.toObject(Product.class);
            productList.add(product);
        }
        return productList;

    }
}
