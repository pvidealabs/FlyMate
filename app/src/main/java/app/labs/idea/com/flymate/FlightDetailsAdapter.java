package app.labs.idea.com.flymate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vijin on 18/01/2015.
 */
public class FlightDetailsAdapter extends ArrayAdapter<FlightDetails> {

    Context context;

    public FlightDetailsAdapter(Context context, int resourceId, List<FlightDetails> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder {
        TextView timings;
        TextView flightNames;
        TextView duration;
        TextView stops;
        TextView stopCities;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        FlightDetails rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.flight_card, null);
            holder = new ViewHolder();
            holder.timings = (TextView) convertView.findViewById(R.id.timings);
            holder.flightNames = (TextView) convertView.findViewById(R.id.flightNames);
            holder.duration = (TextView) convertView.findViewById(R.id.duration);
            holder.stops = (TextView) convertView.findViewById(R.id.stops);
            holder.stopCities = (TextView) convertView.findViewById(R.id.stopCities);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.timings.setText(rowItem.getTimings());
        holder.flightNames.setText(rowItem.getFlightNames());
        holder.duration.setText(rowItem.getDuration());
        holder.stops.setText(rowItem.getStops());
        holder.stopCities.setText(rowItem.getStopCities());

        return convertView;
    }
}
