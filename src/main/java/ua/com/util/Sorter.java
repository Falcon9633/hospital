package ua.com.util;

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
     * @param beans to be sorted
     * @param sortDir           Either 'asc' or 'desc'
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

    public static void specializationsByName(List<Specialization> spec, String sortDir, Locale locale) {
        Collator collator = Collator.getInstance(locale.getJavaLocale());

        if (locale == Locale.EN){
            sortSpecialization(spec, Specialization::getNameEN, collator, sortDir);
        }
        if (locale == Locale.UA){
            sortSpecialization(spec, Specialization::getNameUA, collator, sortDir);
        }
    }

    private static void sortSpecialization(List<Specialization> spec, Function<Specialization, String> getFieldFunction,
                                           Collator collator, String sortDir){
        if (ASC.equals(sortDir)){
            spec.sort(Comparator.comparing(getFieldFunction, collator));
        } else {
            spec.sort(Comparator.comparing(getFieldFunction, collator).reversed());
        }
    }
}
