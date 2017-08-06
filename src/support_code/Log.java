/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support_code;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Suraj Malviya
 * Date Created: 28/02/2017
 */
public class Log implements Serializable
{
    private String user, timesptamp, detail;
    private Date date;

    public Log(String user, String timesptamp, String detail) {
        this.user = user;
        this.timesptamp = timesptamp;
        this.detail = detail;
        
        this.date=new Date();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimesptamp() {
        return timesptamp;
    }

    public void setTimesptamp(String timesptamp) {
        this.timesptamp = timesptamp;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.timesptamp, other.timesptamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Log{" + "user=" + user + ", timesptamp=" + timesptamp + ", detail=" + detail + ", date=" + date + '}';
    }

   

}

