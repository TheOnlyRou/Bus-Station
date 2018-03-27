/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus.station;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "trips", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "Trips.findAll", query = "SELECT t FROM Trips t")
    , @NamedQuery(name = "Trips.findByTripID", query = "SELECT t FROM Trips t WHERE t.tripID = :tripID")
    , @NamedQuery(name = "Trips.findByDate", query = "SELECT t FROM Trips t WHERE t.date = :date")
    , @NamedQuery(name = "Trips.findByDriver", query = "SELECT t FROM Trips t WHERE t.driver = :driver")
    , @NamedQuery(name = "Trips.findByDestination", query = "SELECT t FROM Trips t WHERE t.Destination = :Destination")
    , @NamedQuery(name = "Trips.findByPrice", query = "SELECT t FROM Trips t WHERE t.price = :price")})
public class Trips implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tripID")
    private String tripID;
    @Column(name = "date")
    private String date;
    @Column(name = "driver")
    private String driver;
    @Column(name = "Destination")
    private String Destination;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;

    public Trips() {
    }

    public Trips(String tripID) {
        this.tripID = tripID;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        String oldTripID = this.tripID;
        this.tripID = tripID;
        changeSupport.firePropertyChange("tripID", oldTripID, tripID);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String oldDate = this.date;
        this.date = date;
        changeSupport.firePropertyChange("date", oldDate, date);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        String oldDriver = this.driver;
        this.driver = driver;
        changeSupport.firePropertyChange("driver", oldDriver, driver);
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        String oldDestination = this.Destination;
        this.Destination = Destination;
        changeSupport.firePropertyChange("Destination", oldDestination, Destination);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        Double oldPrice = this.price;
        this.price = price;
        changeSupport.firePropertyChange("price", oldPrice, price);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripID != null ? tripID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trips)) {
            return false;
        }
        Trips other = (Trips) object;
        if ((this.tripID == null && other.tripID != null) || (this.tripID != null && !this.tripID.equals(other.tripID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bus.station.Trips[ tripID=" + tripID + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
