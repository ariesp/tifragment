package ac.id.gunadarma.tifragment.form;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

/**
 * @author ariesp
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewBar implements Serializable {
	private static final long serialVersionUID = 3083209925024706957L;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "startDate")
	private String startDate;

	@JsonProperty(value = "endDate")
	private String endDate;
	
	@JsonProperty(value = "minRange")
	private String minRange;
	
	@JsonProperty(value = "tasks")
	private List<String> tasks;
	
	@JsonProperty(value = "series")
	private List<Series> series;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Series implements Serializable {
		private static final long serialVersionUID = 970061789219477210L;
				
		public Series() {}
		
		@JsonProperty(value = "name")
		private String name;
		
		@JsonProperty(value = "data")
		private List<Range> data;		
		
		@JsonDeserialize
		public static class Range implements JsonSerializable {			
			private int x;
			
			private int y;

			public int getX() {
				return x;
			}

			public void setX(int x) {
				this.x = x;
			}

			public int getY() {
				return y;
			}

			public void setY(int y) {
				this.y = y;
			}

			@Override
			public void serialize(final JsonGenerator jgen, final SerializerProvider provider) 
					throws IOException, JsonProcessingException {
				jgen.writeStartArray();
				if (x == 0 || y == 0) {
					jgen.writeNull();
					jgen.writeNull();
				} else {
					jgen.writeNumber(x);
					jgen.writeNumber(y);					
				}
				jgen.writeEndArray();
			}

			@Override
			public void serializeWithType(final JsonGenerator jgen, final SerializerProvider provider, final TypeSerializer typeSer) 
					throws IOException, JsonProcessingException {
				typeSer.writeTypePrefixForArray(this, jgen);
				serialize(jgen, provider);
				typeSer.writeTypeSuffixForArray(this, jgen);
			}			
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Range> getData() {
			return data;
		}

		public void setData(List<Range> data) {
			this.data = data;
		}			
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getMinRange() {
		return minRange;
	}

	public void setMinRange(String minRange) {
		this.minRange = minRange;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}
}