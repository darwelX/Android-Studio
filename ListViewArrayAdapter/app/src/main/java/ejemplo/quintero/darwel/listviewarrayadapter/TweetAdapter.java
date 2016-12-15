package ejemplo.quintero.darwel.listviewarrayadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Darwel on 13/12/2016.
 * La clase adapter es la encargada de comunicar
 * el componente ListView con los datos necesarios
 * para llenar una lista.
 * Ademas de proveer informacion, Tambien genera
 * los View para cada elemento del ListView.
 * La clase de la cual extienda la clase adapter
 * depende de la naturaleza del ListView, se puede
 * usar los adaptadores disponibles en el SDK o
 * crear uno propio de acuerdo a nuestras necesidades
 */
public class TweetAdapter extends ArrayAdapter<Tweet>{
    Context context;
    int layoutResourceID;
    Tweet data[]=null;

    /*
     * @context representa el contexto de la aplicación
     * @layoutResourceID representa el layout que representara cada fila de la lista
     * @data representa el arreglo con el cual se infla el ListView
     */
    public TweetAdapter(Context context, int layoutResourceID, Tweet[] data){
        super(context, layoutResourceID, data);
        this.context=context;
        this.layoutResourceID=layoutResourceID;
        this.data=data;
    }

    /*
     * Retorna el View inflado y vinculado a los datos segun su posicion
     * @position posicion del elemento View que sera procesado
     * @convertView representa el elemento View a procesar, puede ser null sino se a inflado
     * @parent El elemento padre al cual el item sera insertado, para este caso el ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        TweetHolder holder=null;

        //si el View no existe se procede a inflar
        if(row == null){
            //se prepara el elemento View para ser inflado
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(this.layoutResourceID,parent,false);
            holder = new TweetHolder();
            //se obtiene las referencia de la interfaz del layout que representa cada elemento
            holder.imageView=(ImageView)row.findViewById(R.id.imageView);
            holder.textView=(TextView)row.findViewById(R.id.textView);
            row.setTag(holder);
        }else{
            //si existe retornamos el elemento
            holder = (TweetHolder) row.getTag();
        }
        //se obtiene el tweet actual
        Tweet tweet = data[position];

        //se setean los elementos que componen el diseño del ListView
        holder.imageView.setImageResource(tweet.getImage());
        holder.textView.setText(tweet.getTitle());

        return row;
    }


    /*
     * Esta clase hace uso del patron ViewHolder el cual Google recomienda usar para reducir el
     * trabajo considerablemente ya que el adaptador personalizado llama una gran cantidad de veces
     * los metodos findViewById para obtener las referencias de los View del Item.
     *
     * La clase se usa para almacenar las referncias de los views hijos del Item con la finalidad
     * de acceder a ellos en un futuro
     */
    static class TweetHolder{
        ImageView imageView;
        TextView textView;
    }
}
