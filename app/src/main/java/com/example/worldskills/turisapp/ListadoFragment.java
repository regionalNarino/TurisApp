package com.example.worldskills.turisapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldskills.turisapp.Other.Conexion;
import com.example.worldskills.turisapp.Other.Lugares;
import com.example.worldskills.turisapp.Other.RecyclerAdapter;
import com.example.worldskills.turisapp.Other.Variables;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListadoFragment newInstance(String param1, String param2) {
        ListadoFragment fragment = new ListadoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    View vista;
    ArrayList<Lugares> listaLugares;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_listado, container, false);
        recycler=vista.findViewById(R.id.recyclerview);
        cargarLista();

        return vista;
    }

    private void cargarLista() {

        listaLugares=new ArrayList<>();
        String cadenaSQL="select * from turismo where categoria=";
        if (Variables.categoria.equals("sitios")){
            cadenaSQL+="'sitios'";
            Toast.makeText(getActivity(), "sitios", Toast.LENGTH_SHORT).show();
        }
        if (Variables.categoria.equals("hoteles")){
            cadenaSQL+="'hoteles'";
        }
        if (Variables.categoria.equals("restaurantes")){
            cadenaSQL+="'restaurantes'";
        }

        Conexion conexion=new Conexion(getActivity());
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor=db.rawQuery(cadenaSQL,null);
        if (cursor!=null){
            Toast.makeText(getActivity(), "cargando", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                listaLugares.add(new Lugares(cursor.getString(0),cursor.getString(0),cursor.getString(0),
                        cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0)));
            }
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            RecyclerAdapter adapter=new RecyclerAdapter(listaLugares);
            recycler.setAdapter(adapter);

        }else{
            Toast.makeText(getActivity(), "ningun resultado", Toast.LENGTH_SHORT).show();
        }
    }

    private void verificarCategoria() {
        if (Variables.categoria.equals("sitios")){
            Toast.makeText(getActivity(), "sitios", Toast.LENGTH_SHORT).show();
        }
        if (Variables.categoria.equals("hoteles")){
            Toast.makeText(getActivity(), "hoteles", Toast.LENGTH_SHORT).show();
        }
        if (Variables.categoria.equals("restaurantes")){
            Toast.makeText(getActivity(), "restaurates", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}