package com.example.worldskills.turisapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

        setHasOptionsMenu(true);

        if (vista.findViewById(R.id.detailLand)!=null){
            cargarDatosLand();
        }else{
            Variables.land=false;
        }

        recycler=vista.findViewById(R.id.recyclerview);
        if (Variables.tipoLista.equals("list")){
            cargarLista();
        }else{
            cargarGrid();
        }

        return vista;
    }

    private void cargarDatosLand() {
        Variables.tipoLista="list";
        Variables.land=true;
        Variables.id="1";
        cargarLista();
        cargarDetalle();
    }

    private void cargarGrid() {
        listaLugares=new ArrayList<>();
        String cadenaSQL="select * from turismo where categoria=";
        if (Variables.categoria.equals("sitios")){
            cadenaSQL+="'sitios'";
        }
        if (Variables.categoria.equals("hoteles")){
            cadenaSQL+="'hoteles'";
        }
        if (Variables.categoria.equals("restaurantes")){
            cadenaSQL+="'restaurantes'";
        }

        try{
            Conexion conexion=new Conexion(getActivity());
            SQLiteDatabase db=conexion.getReadableDatabase();
            Cursor cursor=db.rawQuery(cadenaSQL,null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    listaLugares.add(new Lugares(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                            cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
                }
                recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
                RecyclerAdapter adapter=new RecyclerAdapter(listaLugares);//cargar el adaptador de los lugares
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Variables.id=listaLugares.get(recycler.getChildAdapterPosition(v)).getId();
                        getFragmentManager().beginTransaction().replace(R.id.contenedorPrincipal,new DetalleFragment()).addToBackStack(null).commit();
                    }
                });
                recycler.setAdapter(adapter);

            }else{
                Toast.makeText(getActivity(), "ningun resultado", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (vista.findViewById(R.id.detailLand)==null){
            if (id == R.id.change_view) {
                cambiarVista();
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void cambiarVista() {
        Toast.makeText(getActivity(), "cambiar vista", Toast.LENGTH_SHORT).show();
        if (Variables.tipoLista.equals("list")){
            Variables.tipoLista="grid";
            cargarGrid();
        }else{
            Variables.tipoLista="list";
            cargarLista();


        }
    }

    private void cargarLista() {
        listaLugares=new ArrayList<>();
        String cadenaSQL="select * from turismo where categoria=";
        if (Variables.categoria.equals("sitios")){
            cadenaSQL+="'sitios'";
        }
        if (Variables.categoria.equals("hoteles")){
            cadenaSQL+="'hoteles'";
        }
        if (Variables.categoria.equals("restaurantes")){
            cadenaSQL+="'restaurantes'";
        }

        try{

            Conexion conexion=new Conexion(getActivity());
            SQLiteDatabase db=conexion.getReadableDatabase();
            Cursor cursor=db.rawQuery(cadenaSQL,null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    listaLugares.add(new Lugares(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                            cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
                }
                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                RecyclerAdapter adapter=new RecyclerAdapter(listaLugares);//cargar el adaptador de los lugares
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Variables.id=listaLugares.get(recycler.getChildAdapterPosition(v)).getId();
                        if (Variables.land==true){
                            cargarDetalle();
                        }else{
                            getFragmentManager().beginTransaction().replace(R.id.contenedorPrincipal,new DetalleFragment()).addToBackStack(null).commit();
                        }
                    }
                });
                recycler.setAdapter(adapter);

            }else{
                Toast.makeText(getActivity(), "ningun resultado", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }
    }

    private void cargarDetalle() {
        ImageView imagen=vista.findViewById(R.id.imagenDetalleLand);
        TextView lblNombre=vista.findViewById(R.id.nombreDetalleLand);
        TextView lblDescripcion=vista.findViewById(R.id.descripcionDetalleLand);

        Conexion conexion=new Conexion(getActivity());
        SQLiteDatabase data=conexion.getReadableDatabase();

        Cursor cursor=data.rawQuery("select * from turismo where id='"+Variables.id+"'",null);
        cursor.moveToNext();
        imagen.setImageResource(Integer.parseInt(cursor.getString(8)));
        lblNombre.setText(cursor.getString(1));
        lblDescripcion.setText(cursor.getString(4));
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
