import { Component, OnDestroy } from '@angular/core'
import { CommonModule } from '@angular/common'
import { HttpClient, HttpClientModule } from '@angular/common/http'
import { interval, Subscription, switchMap } from 'rxjs'

interface MessageDTO { version?: string; hostname?: string }

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  template: `
  <div class="container">
    <h1>Messages (grouped)</h1>
    <div class="controls">
      <label>Polling: <input type="checkbox" [(ngModel)]="polling"></label>
      <span *ngIf="lastUpdated">Last: {{ lastUpdated | date:'mediumTime' }}</span>
    </div>

    <div *ngIf="!hasMessages()">No messages</div>

    <div *ngFor="let host of hostKeys()" class="host-block">
      <h2>{{ host }}</h2>
      <ul>
        <li *ngFor="let ver of versionKeys(host)">{{ ver }}: {{ groups[host][ver] }}</li>
      </ul>
    </div>
  </div>
  `,
  styles: [`.container{max-width:800px;margin:2rem auto}.host-block{border:1px solid #eee;padding:.5rem;margin:.5rem 0}`]
})
export class AppComponent implements OnDestroy {
  polling = true
  lastUpdated: Date | null = null
  groups: Record<string, Record<string, number>> = {}
  sub: Subscription

  constructor(private http: HttpClient) {
    this.sub = interval(1000)
      .pipe(switchMap(() => this.polling ? this.http.get<MessageDTO[]>('/messages') : []) )
      .subscribe({ next: (data: any) => this.handleData(data), error: (e) => console.error(e) })
  }

  handleData(data: MessageDTO[] | any) {
    if (!Array.isArray(data)) return
    this.groups = {}
    for (const m of data) {
      const host = m.hostname || 'unknown'
      const ver = m.version || 'unknown'
      if (!this.groups[host]) this.groups[host] = {}
      this.groups[host][ver] = (this.groups[host][ver] || 0) + 1
    }
    this.lastUpdated = new Date()
  }

  hasMessages() { return Object.keys(this.groups).length > 0 }
  hostKeys() { return Object.keys(this.groups) }
  versionKeys(h: string) { return Object.keys(this.groups[h] || {}) }

  ngOnDestroy() { this.sub.unsubscribe() }
}
