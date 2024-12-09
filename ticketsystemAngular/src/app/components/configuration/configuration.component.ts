import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-configuration',
  template: `
    <h2>Configuration Input</h2>
    <form (ngSubmit)="onSubmit()">
      <div>
        <label for="maxTickets">Maximum Tickets:</label>
        <input type="number" id="maxTickets" [(ngModel)]="config.maxTickets" name="maxTickets" required>
      </div>
      <div>
        <label for="numVendors">Number of Vendors:</label>
        <input type="number" id="numVendors" [(ngModel)]="config.numVendors" name="numVendors" required>
      </div>
      <div>
        <label for="numCustomers">Number of Customers:</label>
        <input type="number" id="numCustomers" [(ngModel)]="config.numCustomers" name="numCustomers" required>
      </div>
      <button type="submit">Start Simulation</button>
    </form>
  `,
  standalone: true,
  imports: [
    FormsModule
  ]
})
export class ConfigurationComponent {
  config = { maxTickets: 0, numVendors: 0, numCustomers: 0 };

  onSubmit() {
    console.log('Configuration Submitted:', this.config);
  }
}

