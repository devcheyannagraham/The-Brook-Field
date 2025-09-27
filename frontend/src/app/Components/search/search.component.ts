import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ReportsService } from '../../Services/reports.service';

@Component({
  selector: 'search-component',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  searchInput = new FormControl();

  constructor(public reportService: ReportsService) { }

  search() {
    console.log("searching")
    let value = this.searchInput.value;
    if (value.trim() != null) {
      this.reportService.searchTerms(value);

    }
  }

}
