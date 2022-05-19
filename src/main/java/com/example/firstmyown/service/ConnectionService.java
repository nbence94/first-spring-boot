package com.example.firstmyown.service;

import com.example.firstmyown.model.ConnectionPrimaries;
import com.example.firstmyown.model.Connections;
import com.example.firstmyown.repository.ConnectionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {

    private final ConnectionRepository conn_repo;

    public ConnectionService(ConnectionRepository connection_repository) {
        this.conn_repo = connection_repository;
    }

    public Connections insertKapcsolat(int szotarid, int szoid) {
        if(szoid != -1 && szotarid != -1) {
            Connections conn = new Connections();
            conn.setId(new ConnectionPrimaries(szotarid, szoid));

            return conn_repo.save(conn);
        } else return null;
    }

}









