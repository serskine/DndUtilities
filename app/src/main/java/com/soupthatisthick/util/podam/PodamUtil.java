package com.soupthatisthick.util.podam;

import java.lang.reflect.Type;
import java.util.ArrayList;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PodamUtil {

    // Constants ----------------------------------------------------------------- Constants //

    private static final PodamFactory podamFactory = new PodamFactoryImpl();

    // Instance Variables ----------------------------------------------- Instance Variables //

    // Constructors ----------------------------------------------------------- Constructors //

    // Public Methods ------------------------------------------------------- Public Methods //

    public static <T> ArrayList<T> manufactureList(Class<T> classType, int total) {
        ArrayList<T> list = new ArrayList<>();
        for(int i=0; i<total; i++) {
            list.add(podamFactory.manufacturePojo(classType));
        }
        return list;
    }

    public static <T> T manufacture(Class<T> classType) {
        return podamFactory.manufacturePojo(classType);
    }

    public static <T> T manufacture(Class<T> classType, Type... genericTypes) {
        return podamFactory.manufacturePojo(classType, genericTypes);
    }

    // Protected Methods ------------------------------------------------- Protected Methods //

    // Private methods ----------------------------------------------------- Private methods //

    // Getters & Setters ------------------------------------------------- Getters & Setters //

} // End of class
