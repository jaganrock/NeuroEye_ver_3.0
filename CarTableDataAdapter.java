package ragul.srushty.com.neuroeye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ragul.srushty.com.neuroeye.data.Car;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by karthi2 on 9/15/2016.
 */


public class CarTableDataAdapter extends TableDataAdapter<Car> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public CarTableDataAdapter(final Context context, final List<Car> data) {
        super(context, data);
    }

    @Override
    public View getCellView(final int rowIndex, final int columnIndex, final ViewGroup parentView) {
        final Car car = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView =  renderPatientId(car);
                break;
            case 1:
                renderedView = renderPatientName(car);
                break;
            case 2:
                renderedView = renderPatientMobile(car);
                break;
            case 3:
                renderedView = renderPatientEmail(car);
                break;
            case 4:
                renderedView = renderPatientAge(car);
                break;
            case 5:
                renderedView = renderPatientGender(car);
                break;
            case 6:
                renderedView = renderTestDate(car);
                break;
        }
        return renderedView;
    }






    private View renderPatientName(final Car car) {
        return renderString(car.getPatientName());
    }



    private View renderPatientId(final Car car) {
        return renderString(car.getPatientId());
    }



    private View renderPatientAge(final Car car) {
        return renderString(car.getPatientAge());
    }

    private View renderPatientMobile(final Car car) {
        return renderString(car.getPatientMobile());
    }

    private View renderPatientEmail(final Car car) {
        return renderString(car.getPatientEmail());
    }

    private View renderPatientGender(final Car car) {
        return renderString(car.getPatientGender());
    }


    private View renderTestDate(final Car car) {

        car.getTestDate();
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String ssk= df.format(car.getTestDate());
        return renderString(ssk);

    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 25, 20, 25);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}
