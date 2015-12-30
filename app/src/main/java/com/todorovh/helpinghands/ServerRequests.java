package com.todorovh.helpinghands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ServerRequests {
    ProgressDialog progressDialog;
    public static final String SERVER_ADDRESS = "http://todorovh.site88.net/";
    public static final String REGISTER = SERVER_ADDRESS + "Register.php";
    public static final String FETCH_USER_DATA = SERVER_ADDRESS + "FetchUserData.php";
    RequestQueue requestQueue;

    public ServerRequests(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Please wait...");

        requestQueue = Volley.newRequestQueue(context);
    }

    public void storeUserDataInBackground(User user, GetUserCallBack userCallBack){
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallBack callBack){
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User user;
        GetUserCallBack userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallBack userCallBack){
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {

            StringRequest request = new StringRequest(Request.Method.POST,
                    REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters = new HashMap<>();
                    parameters.put("name", user.name);
                    parameters.put("age", user.age + "");
                    parameters.put("city", user.city);
                    parameters.put("address", user.address);
                    parameters.put("phone", user.phone);
                    parameters.put("email", user.email);
                    parameters.put("username", user.username);
                    parameters.put("password", user.password);

                    return parameters;
                }
            };
            requestQueue.add(request);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack userCallBack;
        User returnedUser;

        public FetchUserDataAsyncTask(User user, GetUserCallBack userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    FETCH_USER_DATA, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject user = response.getJSONObject("user");

                            String name = user.getString("name");
                            int age = user.getInt("age");
                            String city = user.getString("city");
                            String address = user.getString("address");
                            String phone = user.getString("phone");
                            String email = user.getString("email");
                            String username = user.getString("username");

                            returnedUser = new User(name, age, city,
                                    address, phone, email, username);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters = new HashMap<>();
                    parameters.put("username", LoginActivity.usernameStr);
                    parameters.put("password", LoginActivity.passwordStr);

                    return parameters;
                }
            };
            requestQueue.add(jsonObjectRequest);
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
}
