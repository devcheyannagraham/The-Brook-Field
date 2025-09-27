import { Component, output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ReportsService } from '../../Services/reports.service';
import { InventoryCountDto } from '../../DTOs/Report/InventoryCountDto';

@Component({
  selector: 'search-component',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  searchInput = new FormControl();
  searchEvent = output<InventoryCountDto[]>();

  constructor(public reportService: ReportsService) { }

  search() {
    console.log("searching")
    let value = this.searchInput.value;
    if (value.trim() != null) {
      this.reportService.searchTerms(value)
        .then(data => {
          this.searchEvent.emit(data || []);
        });
    }
  }
}
