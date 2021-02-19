package ua.com.util;

import ua.com.bean.DoctorAccountBean;
import ua.com.bean.SpecializationAccountDetailsBean;
import ua.com.entity.Locale;
import ua.com.entity.Specialization;

import javax.servlet.http.HttpServletRequest;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static ua.com.constant.SorterConstants.*;

public class Sorter {

    private Sorter() {
        // utility class
    }

    /**
     * Sorts given list depends on the given locale and sorting direction.
     * Then send opposite direction as a request attribute to the page.
     *
     * @param beans   to be sorted
     * @param sortDir Either 'asc' or 'desc'
     */
    public static void sortSpecAccDetListByName(List<SpecializationAccountDetailsBean> beans,
                                                Locale locale,
                                                String sortDir,
                                                HttpServletRequest req) {
        Collator collator = Collator.getInstance(locale.getJavaLocale());

        if (locale == Locale.EN) {
            sortSpecAccDetList(beans, SpecializationAccountDetailsBean::getNameEN, collator, sortDir, req);
        }
        if (locale == Locale.UA) {
            sortSpecAccDetList(beans, SpecializationAccountDetailsBean::getNameUA, collator, sortDir, req);
        }

    }

    private static void sortSpecAccDetList(List<SpecializationAccountDetailsBean> beans,
                                           Function<SpecializationAccountDetailsBean, String> getFieldFunction,
                                           Collator collator, String sortDir, HttpServletRequest req) {
        if (ASC.equals(sortDir)) {
            beans.sort(Comparator.comparing(getFieldFunction, collator));
            req.setAttribute("sortDirName", DESC);
        } else {
            beans.sort(Comparator.comparing(getFieldFunction, collator).reversed());
            req.setAttribute("sortDirName", ASC);
        }
    }

    public static <T> void sortByField(List<T> list, String sortDir, Function<T, String> getFieldFunction, Locale locale) {
        Collator collator = Collator.getInstance(locale.getJavaLocale());

        if (ASC.equals(sortDir)) {
            list.sort(Comparator.comparing(getFieldFunction, collator));
        } else {
            list.sort(Comparator.comparing(getFieldFunction, collator).reversed());
        }
    }

    public static <T> void sortByField(List<T> list, String sortDir, Function<T, Integer> getFieldFunction) {
        if (ASC.equals(sortDir)) {
            list.sort(Comparator.comparing(getFieldFunction));
        } else {
            list.sort(Comparator.comparing(getFieldFunction).reversed());
        }
    }
}
