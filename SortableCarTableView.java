package ragul.srushty.com.neuroeye;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import ragul.srushty.com.neuroeye.data.Car;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;


/**
 * An extension of the {@link SortableTableView} that handles {@link Car}s.
 *
 * @author ISchwarz
 */
public class SortableCarTableView extends SortableTableView<Car> {

    public SortableCarTableView(final Context context) {
        this(context, null);
    }

    public SortableCarTableView(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public SortableCarTableView(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, R.string.patient_id, R.string.patient_name, R.string.mob, R.string.ema, R.string.patient_age, R.string.patient_gender,R.string.testdate);

        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.white_text));
        setHeaderAdapter(simpleTableHeaderAdapter);

        final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        setColumnWeight(0, 1);
        setColumnWeight(1, 1);
        setColumnWeight(2, 1);
        setColumnWeight(3, 1);
        setColumnWeight(4, 1);
        setColumnWeight(5, 1);
        setColumnWeight(6, 1);

        setColumnComparator(0, CarComparators.getPatientIdComparator());
        setColumnComparator(1, CarComparators.getNameComparator());
        setColumnComparator(2, CarComparators.getPatientMobileComparator());
        setColumnComparator(3, CarComparators.getPatientEmailComparator());
        setColumnComparator(4, CarComparators.getPatientAgeComparator());
        setColumnComparator(5, CarComparators.getPatientGenderComparator());
        setColumnComparator(6, CarComparators.getTestDateComparator());
    }

}