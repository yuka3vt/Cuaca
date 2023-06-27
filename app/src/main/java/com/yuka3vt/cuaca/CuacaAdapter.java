package com.yuka3vt.cuaca;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CuacaAdapter extends RecyclerView.Adapter<CuacaViewHolder> {
    private List<ListModel> listModelList;
    private RootModel rm;

    public CuacaAdapter(RootModel rm) {
        this.rm = rm;

        listModelList = rm.getListModelList();
    }

    @NonNull
    @Override
    public CuacaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cuaca,parent,false);
        return new CuacaViewHolder(view);
    }

    private double toCelcius(double kelvin){return kelvin-272.15;}

    private String formatNumber(double number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }

    @Override
    public void onBindViewHolder(@NonNull CuacaViewHolder holder, int position) {
        ListModel lm = listModelList.get(position);
        WeatherModel wm = lm.getWeatherModelList().get(0);
        MainModel mm = lm.getMainModel();

        String suhu = formatNumber(toCelcius(mm.getTemp_min()),"###.##") + "°C - " + formatNumber(toCelcius(mm.getTemp_max()),"###.##") + "°C";
        switch (wm.getIcon()){
            case "01d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_01d);
                break;
            case "01n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_01n);
                break;
            case "02d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_02d);
                break;
            case "02n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_02n);
                break;
            case "03d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_03d);
                break;
            case "03n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_03n);
                break;
            case "04d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_04d);
                break;
            case "04n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_04n);
                break;
            case "09d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_09d);
                break;
            case "09n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_09n);
                break;
            case "10d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_10d);
                break;
            case "10n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_10n);
                break;
            case "11d":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_11d);
                break;
            case "11n":
                holder.cuacaImageView.setImageResource(R.mipmap.ic_11n);
                break;
        }

        String tanggalWaktuWib = formatWib(lm.getDt_txt());

        holder.namaTextView.setText(wm.getMain());
        holder.deskripsiTextView.setText(wm.getDescription());
        holder.suhuTextView.setText(suhu);

    }

    @Override
    public int getItemCount() {
        return (listModelList != null) ? listModelList.size() : 0;
    }

    private String formatWib(String waktuGMT){
        Log.d("*YW*","Waktu GMT : " + waktuGMT);

        Date waktu = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            waktu = sdf.parse(waktuGMT);
        } catch (ParseException e) {
            Log.e("*YW*" , e.getMessage());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(waktu);
        calendar.add(Calendar.HOUR_OF_DAY,7);

        Date waktuWIB = calendar.getTime();

        String _waktuWIB = sdf.format(waktuWIB);
        _waktuWIB = _waktuWIB.replace("00:00","00 WIB");

        Log.d("*YW*","Waktu WIB : " +_waktuWIB);

        return _waktuWIB;
    }
}
