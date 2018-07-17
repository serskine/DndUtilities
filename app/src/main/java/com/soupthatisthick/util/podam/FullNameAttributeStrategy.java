package com.soupthatisthick.util.podam;

import io.codearte.jfairy.Fairy;
import uk.co.jemos.podam.common.AttributeStrategy;

public class FullNameAttributeStrategy implements AttributeStrategy<String> {

    // Constants ---------------------------------------------------------------------------------------------- Constants

    // Instance Variables ---------------------------------------------------------------------------- Instance Variables

    // Constructors ---------------------------------------------------------------------------------------- Constructors

    // Public Methods ------------------------------------------------------------------------------------ Public Methods

    @Override
    public String getValue() {
        return Fairy.create().person().getFullName();
    }

    // Protected Methods ------------------------------------------------------------------------------ Protected Methods

    // Private Methods ---------------------------------------------------------------------------------- Private Methods

    // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class

