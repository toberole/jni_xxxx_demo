package com.xiaoge.org.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
   public String name;
   public int age;

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(this.name);
      dest.writeInt(this.age);
   }

   public User() {
   }

   protected User(Parcel in) {
      this.name = in.readString();
      this.age = in.readInt();
   }

   public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
      @Override
      public User createFromParcel(Parcel source) {
         return new User(source);
      }

      @Override
      public User[] newArray(int size) {
         return new User[size];
      }
   };
}
