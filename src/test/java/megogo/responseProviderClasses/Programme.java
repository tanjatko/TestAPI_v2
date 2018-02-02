package megogo.responseProviderClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Programme
{
    private String id;

    private Genre genre;

    private String stop;

    private Title title;

    private Category category;

    private String start;

    private String category_id;

    private String date;

    private String channel;

    private String genre_id;

    private Desc desc;

    private String production_year;

    @XmlElement
    public String getProduction_year ()
    {
        return production_year;
    }
    public void setProduction_year(String production_year)
    {
        this.production_year = production_year;
    }

    @XmlElement
    public Desc getDesc () {return desc;}
    public void setDesc(Desc desc) {this.desc = desc;}

    @XmlElement
    public String getId ()
    {
        return id;
    }
    public void setId (String id)
    {
        this.id = id;
    }

    @XmlElement
    public Genre getGenre ()
    {
        return genre;
    }

    public void setGenre (Genre genre)
    {
        this.genre = genre;
    }
    @XmlAttribute
    public String getStop ()
    {
        return stop;
    }

    public void setStop (String stop)
    {
        this.stop = stop;
    }

    @XmlElement
    public Title getTitle ()
    {
        return title;
    }

    public void setTitle (Title title)
    {
        this.title = title;
    }
    @XmlElement
    public Category getCategory ()
    {
        return category;
    }

    public void setCategory (Category category)
    {
        this.category = category;
    }

    @XmlAttribute
    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }
    @XmlElement
    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }
    @XmlAttribute
    public String getChannel ()
    {
        return channel;
    }

    public void setChannel (String channel)
    {
        this.channel = channel;
    }
    @XmlAttribute
    public String getGenre_id ()
    {
        return genre_id;
    }

    public void setGenre_id (String genre_id)
    {
        this.genre_id = genre_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", genre = "+genre+", stop = "+stop+", title = "+title+", category = "+category+", start = "+start+", category_id = "+category_id+", date = "+date+", channel = "+channel+", genre_id = "+genre_id+"]";
    }
}

