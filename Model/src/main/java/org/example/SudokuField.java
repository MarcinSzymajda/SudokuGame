package org.example;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;




public class SudokuField implements Serializable,Comparable<SudokuField>,Cloneable {

    private int value;

    public SudokuField() {

    }

    public SudokuField(SudokuField sudokuField) {
        this.value = sudokuField.value;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value1) {
        value = value1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public int compareTo(SudokuField o) throws NullPointerException {
        Integer b = this.getFieldValue();
        return b.compareTo(o.getFieldValue());
    }


}
