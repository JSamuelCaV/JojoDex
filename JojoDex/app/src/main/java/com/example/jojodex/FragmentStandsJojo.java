package com.example.jojodex;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentStandsJojo extends Fragment implements Response.Listener<JSONArray>,Response.ErrorListener {
    private RecyclerView recyclerView;

    RequestQueue queue;

    JsonArrayRequest js;
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stands_jojo, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_stand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        queue = Volley.newRequestQueue(getContext());
        editText = view.findViewById(R.id.busqueda_stand);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    String texto = editText.getText().toString();
                    cargaDatos(texto);
                    return true;
                }
                return false;
            }
        });

        return view;
    }
//Se filtra por el valor que introduce el usuario dentro del editText y pulsar enter buscará el texto que ha escrito

    public void cargaDatos(String texto) {
        String url = "https://63e9e3c7811db3d7ef02768b.mockapi.io/JojoData?search=" + texto;
        js=new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(js);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "could not connect" + error.toString(), Toast.LENGTH_SHORT).show();
        System.out.println();

        Log.d("Error: ", error.toString());

    }
// Conecta con mockapi para poder usar los datos

    @Override
    public void onResponse(JSONArray response) {
        List<DatosStands> todosLosStands =new ArrayList<DatosStands>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject stands = response.getJSONObject(i);
                DatosStands datos = new DatosStands(stands);
                todosLosStands.add(datos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerViewStandsAdapter adapter = new RecyclerViewStandsAdapter(todosLosStands, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }
    //Se pasan los datos recibidos del mockapi a un recyclerView

}
