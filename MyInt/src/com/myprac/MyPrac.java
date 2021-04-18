package com.myprac;

public class MyPrac {

    static boolean x = doubleX("xaxxx");

    public static void main(String[] args) {
        System.out.println(x);
    }

    static boolean doubleX(String str) {
        boolean x = false;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.substring(i, i + 2).equals("xx")) {
                x = true;
                break;
            }
        }
        return x;
    }


    private String loadCT() {




        ClientResource resource = new ClientResource("http://localhost:8080/someresource");

        Response response = resource.post(form.getWebRepresentation());

        if (response.getStatus().isSuccess()) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity().getText());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity().getText());
        }
    }
}