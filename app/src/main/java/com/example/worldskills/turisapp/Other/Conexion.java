package com.example.worldskills.turisapp.Other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.worldskills.turisapp.MainActivity;
import com.example.worldskills.turisapp.R;

public class Conexion extends SQLiteOpenHelper{
    Context context;
    public Conexion(Context context) {
        super(context, "turisapp", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table turismo (id integer primary key autoincrement, nombre text,descripcionCorta text,ubicacion text, descripcion text,latitud text,longitud text,categoria text,foto text)");
        cargarSitios(db);

    }

    private void cargarSitios(SQLiteDatabase db) {
        db.execSQL("insert into turismo(nombre,descripcionCorta,ubicacion,descripcion,latitud,longitud,categoria,foto) values" +
                "('Cerro de Monserrate','El Cerro de Monserrate los espera durante los 365 días del año.','Carrera 2 E No. 21-48 | Paseo Bolívar, Bogota, Colombia','El cerro de Monserrate es el más conocido de los cerros Orientales de Bogotá. Junto a Guadalupe es uno de los cerros tutelares de la ciudad. Monserrate tiene una altitud de 3152 m y se ubica sobre la cordillera oriental.1\u200B Los cerros de Bogotá, de origen sedimentario, tienen por lo menos 16 millones de años de antigüedad, con rocas de edad cretácica pertenecientes al Grupo guadalupe, en lo que se refiere al aspecto geológico. Hasta mediados del siglo XVII fue conocido como cerro de Las Nieves. La basílica del Señor de Monserrate ha sido lugar de peregrinación religiosa desde la época colonial y se constituye en un atractivo natural, religioso, gastronómico de la ciudad. Se puede ascender al cerro por el sendero peatonal, por teleférico o por funicular.','4.6056941','-74.0642803','sitios','"+ R.drawable.monserrate+"'),"+

                "('Parque Metropolitano Simon Bolivar','Área verde urbana con recorridos para caminantes y ciclistas, lago para andar en bote y un área de juego.','Av. Calle 53 y Av. Esmeralda s/n, Bogotá, Cundinamarca','Este es el más grande e importante de la ciudad por varias razones: su estratégica ubicación en el corazón de Bogotá; sus amplios espacios verdes acompañados de un innumerable número de árboles o por la cantidad y variedad de los escenarios que lo conforman.\n" +
                "Este pulmón de la ciudad, que se ha convertido en parte esencial de la vida de los capitalinos, está conformado por:\n" +
                "- El parque Central Simón Bolívar(Calle 63 y 53 entre carreras 48 y 68)\n" +
                "- El parque Los Novios (o parque El Lago)\n" +
                "- El Complejo Acuático\n" +
                "- El Centro de Alto Rendimiento\n" +
                "- La Cancha Popular de Golf\n" +
                "- La Plaza de los Artesanos\n" +
                "- El Salitre PRD (Recreodeportivo)\n" +
                "- El Museo de los Niños\n" +
                "- La biblioteca Virgilio Barco\n" +
                "- La Unidad Deportiva El Salitre\n" +
                "- El Salitre Mágico\n" +
                "- El Jardín Botánico','4.5981259','--74.0782322','sitios','"+R.drawable.plazadebolivar+"'),"+

                "('Centro Comercial Andino','El Centro Comercial Andino es el corazón de una de las zonas preferidas por los bogotanos y extranjeros','Cra. 11 #82-71, Bogotá, Cundinamarca','El Centro Comercial Andino es el corazón de una de las zonas preferidas por los bogotanos y extranjeros, y es reconocido como el Centro Comercial más exclusivo de la ciudad. \n" +
                "\n" +
                "La privilegiada ubicación, ambiente, comodidad, seguridad, excelente servicio y amplia gama de tiendas reconocidas a nivel nacional e internacional, lo convierten en la mejor opción para sus compras y entretenimiento.\n" +
                "\n" +
                "Cuenta con 230 locales comerciales distribuidos en 4 niveles, 700 parqueaderos ubicados en dos sótanos con valet parking y servicios exclusivos para niños y adultos.','4.6667313','-74.0553429','sitios','"+ R.drawable.andino+"'),"+


                "('Centro Comercial Centro Mayor','Angulo\n" +
                        "Arquitectos Contexto Urbano\n" +
                        "Tiendas y servicios 354 tiendas\n" +
                        "Superficie total 300.000 m²\n" +
                        "Estacionamiento 3.141 espacios\n" +
                        "N.º de plantas 3 niveles\n" +
                        "Centro Comercial Centro Mayor\n" +
                        "[editar datos en Wikidata]\n" +
                        "El centro comercial Centro Mayor es un centro comercial ubicado en la localidad de Antonio Nariño, situada al sur de Bogotá','Calle 38 A #Sur No. 34D-51, Bogotá','El centro comercial cuenta con una disposición especial en su interior. Tiene una temática circense adoptada en su estructura y en su decoración. En cuanto a responsabilidad ambiental, su arquitectura bioclimática aprovecha los recursos climáticos (sol, lluvia, vientos) para disminuir los impactos ambientales y reducir el consumo de energía.4\u200B Cada una de las plazoletas en las que se divide el establecimiento tienen por nombre el de algún personaje circense y tienen en ellas esculturas de más de cuatro metros alusivas a dichos personajes:\n" +
                        "\n" +
                        "Plaza del Acordeonista\n" +
                        "Plaza del Mago\n" +
                        "Plaza del Contorsionista\n" +
                        "Plaza del Malabarista\n" +
                        "Plaza del Equilibrista\n" +
                        "Plaza del Payaso\n" +
                        "Plaza del Trapecista (Plaza principal)','4.5926585','-74.1263605','sitios','"+ R.drawable.centromayor +"')"
        );
        Toast.makeText(context, "insertados sitios", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
