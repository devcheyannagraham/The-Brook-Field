import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LowInventoryComponent } from './low-inventory.component';

describe('LowInventoryComponent', () => {
  let component: LowInventoryComponent;
  let fixture: ComponentFixture<LowInventoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LowInventoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LowInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
