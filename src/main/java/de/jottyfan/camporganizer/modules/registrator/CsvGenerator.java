package de.jottyfan.camporganizer.modules.registrator;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanField;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import de.jottyfan.camporganizer.modules.book.PersonBean;

/**
 * 
 * @author jotty
 *
 */
public class CsvGenerator<T> {

	/**
	 * write content of bean and list to csv
	 * 
	 * @param list
	 *          entries of csv
	 * @return csv content
	 * @throws IOException
	 * @throws CsvDataTypeMismatchException
	 * @throws CsvRequiredFieldEmptyException
	 */
	public String generateContent(List<T> list, String headline)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		Writer writer = new StringWriter();
		writer.write(headline);
		StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<T>(writer);
		StatefulBeanToCsv<T> b2c = builder.build();
		b2c.write(list);		
		writer.close();
		return writer.toString();
	}
}
