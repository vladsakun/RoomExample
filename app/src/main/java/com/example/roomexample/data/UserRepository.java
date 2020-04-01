package com.example.roomexample.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application){
        Database database = Database.getInstance(application);
        userDao = database.mDao();
        mAllUsers = this.userDao.getAllUsers();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void updateSpecific(User newUser){
        new UpdateSpecificUserAsyncTask(userDao).execute(newUser);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAll(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }


    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mUserDao;

        private InsertUserAsyncTask(UserDao dao){
            this.mUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mUserDao;

        private UpdateUserAsyncTask(UserDao dao){
            this.mUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.update(users[0]);
            return null;
        }
    }

    private static class UpdateSpecificUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mUserDao;

        private UpdateSpecificUserAsyncTask(UserDao dao){
            this.mUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.updateSpecific("NEW", "new", 1234567, users[0].getId());
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mUserDao;

        private DeleteUserAsyncTask(UserDao dao){
            this.mUserDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.delete(users[0]);
            return null;
        }
    }
    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao mUserDao;

        private DeleteAllUserAsyncTask(UserDao dao){
            this.mUserDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.deleteAll();
            return null;
        }
    }
}
