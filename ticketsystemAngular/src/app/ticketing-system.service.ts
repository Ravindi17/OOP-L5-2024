import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WebSocketSubject, webSocket } from 'rxjs/webSocket';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketingSystemService {
  private baseUrl = 'http://localhost:8080/api'; // Backend API base URL
  private activitySubject: Subject<string> = new Subject<string>();


  constructor(private http: HttpClient) {}

  // API to save configuration
  saveConfiguration(config: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/configurations`, config);
  }

  // API to start the ticketing system
  startSystem(): Observable<any> {
    return this.http.post(`${this.baseUrl}/tickets/start`, {});
  }

  // API to stop the ticketing system
  stopSystem(): Observable<any> {
    return this.http.post(`${this.baseUrl}/tickets/stop`, {});
  }

  // WebSocket for real-time activities
  connectWebSocket(): void {
    this.activitySubject = webSocket('ws://localhost:8080/activities'); // WebSocket endpoint
  }

  // Subscribe to WebSocket messages
  getActivities(): Observable<any> {
    if (!this.activitySubject) {
      this.connectWebSocket();
    }
    return this.activitySubject.asObservable();
  }

  // API to fetch ticket system summary
  getSummary(): Observable<any> {
    return this.http.get(`${this.baseUrl}/tickets/summary`);
  }

  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/api/logs`);
  }

}




