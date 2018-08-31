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
        cargarHoteles(db);
        cargarRestaurantes(db);

    }

    private void cargarHoteles(SQLiteDatabase db) {
        db.execSQL("insert into turismo(nombre,descripcionCorta,ubicacion,descripcion,latitud,longitud,categoria,foto) values" +
                "('Hotel Continental Bluedoors','Hotel sofisticado con un bar restaurante moderno y una cafetería, además de un gimnasio y un spa.'," +
                "'Avenida Jiménez No. 4 - 16\n Candelaria - Centro Historico'," +
                "'Este elegante hotel con todo suites, situado a 4 minutos a pie de los objetos de oro del Museo del Oro, también se encuentra a 8 minutos a pie de las obras de arte del Museo Botero y a 11 minutos a pie de la Plaza de Bolívar, la plaza principal de la ciudad.\n" +
                "Las luminosas y modernas suites disponen de Wi-Fi gratis, televisiones de pantalla plana y minibares, además de cocinas y salas de estar.\n" +
                "El aparcamiento es gratuito. El desayuno se ofrece por un suplemento. Entre el resto de instalaciones que se incluyen en el hotel se encuentran una panadería cafetería, un bar restaurante moderno con terraza, un gimnasio y un spa.'," +
                "'4.6010598','-74.0705079','hoteles','"+ R.drawable.hotelcontinentals+"'),"+

                "('Hotel Tequendama y centro de convenciones','El Hotel Tequendama Bogotá, ubicado en pleno centro de Bogotá y a tan sólo 15 minutos del Aeropuerto Internacional El Dorado'," +
                "'Cra. 10 #26-21, Bogotá, Cundinamarca'," +
                "'El Hotel Tequendama Bogotá, ubicado en pleno centro de Bogotá y a tan sólo 15 minutos del Aeropuerto Internacional El Dorado, ofrece todos los servicios de un hotel 5 estrellas.\n" +
                "\n" +
                "Dispone de 573 habitaciones de estilo clásico totalmente reformadas y dotadas con la última tecnología, todo lo necesario para garantizar el máximo confort de nuestros clientes.\n" +
                "Además, el hotel ofrece wifi gratuito, restaurante, spa y 39 salones que lo convierten en la mejor opción para celebrar cualquier tipo de reunión de negocios o evento empresarial en Bogotá.'," +
                "'4.6128583','-74.0728357','hoteles','"+ R.drawable.hoteltequendama+"')"


        );
    }

    private void cargarRestaurantes(SQLiteDatabase db) {

        db.execSQL("insert into turismo(nombre,descripcionCorta,ubicacion,descripcion,latitud,longitud,categoria,foto) values" +
                "('Juana La Loca','Buenos cocteles, agradable grupos'," +
                "'Calle 90 # 11-13, Bogotá'," +
                "' Del primer menú de Juana La Loca quedan pocos platillos. Los chicharrones de cochinillo con lima y jalapeño son de los pocos que sobreviven a ese difícil comienzo, hace dos años. De ese momento quedarán otros cinco platos, como clásicos de siempre –dice Rafael Londoño, al frente de la marca–. Pero, pasados unos ocho meses de funcionamiento empezamos el proceso de reformar la carta”.\n" +
                "Londoño recuerda que se estrenó en el mundo de los restaurantes con Juana la Loca, cuando se asoció con el grupo Tragaluz de Barcelona. Pero no fue tan sencillo traer las cosas que eran exitosas en la ciudad catalana a Bogotá.'," +
                "'4.672065','-74.05126','restaurantes','"+ R.drawable.juanalaloca+"'),"+


                "('La fragata Giratoria','El Restaurante La Fragata Giratorio es un sitio en donde se puede degustar comida de excelencia a base de productos marinos.'," +
                "'Calle 100 # 8A-55 Piso 12. W.T.C'," +
                "'El Restaurante La Fragata Giratorio es un sitio en donde se puede degustar comida de excelencia a base de productos marinos. Su calidad está garantizada con una tradición de más de cuarenta años en otras ciudades de Colombia. Desde el espacioso y sofisticado local, se alcanza una vista única del norte capitalino y sus cerros, lugar ideal para compartir con la familia, amigos o pasar el rato en solitario. En su carta se destaca el famoso plato Langostino Fragata, acompañado de arroz con coco, plátano gratinado y una salsa exclusiva de la casa. Otras de sus creaciones son el seviche costeño, el seviche peruano, los rollos de anguila y el más completo de todos. langosta 3 sabores. La opción de entrante más solicitada es la sopa caldereta de cangrejos y, para el postre, se ofrecen opciones ligeras, como el postre de natas y la torta de café. La oferta del menú infantil da la oportunidad de reunirse para festejos de todo tipo. Además del comedor, cuenta con un café y una barra de sushi. En el restaurante se celebran festivales de comida nacional e internacional a los que asisten cocineros y chefs extranjeros, y en los que se presentan recetas que enriquecen el menú de La Fragata Giratorio todos los años.'," +
                "'4.6810056','-74.04333','restaurantes','"+ R.drawable.fragata+"')"

        );
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
                "- El Jardín Botánico','4.5981259','-74.0782322','sitios','"+R.drawable.plazadebolivar+"'),"+

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
