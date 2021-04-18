/**
 * 
 */
package ca.ontario.health.hns.util.csv;


public interface CsvProcessor<T> {
	public T process(T inData) throws Exception;
}
