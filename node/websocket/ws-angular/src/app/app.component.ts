import { Component, OnInit } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  message: string;
  messages: string[] = [];
  socket: WebSocketSubject<string>;

  ngOnInit() {
    this.socket = webSocket('ws://localhost:3000');
    this.socket.subscribe(
      (message) => {
        console.log(message);
        this.messages.push(message);
      },
      (err) => console.warn(err),
      () => console.log('Complited')
    );
  }

  click() {
    this.socket.next(this.message);
    console.log('Sende', this.message);
  }
 }
