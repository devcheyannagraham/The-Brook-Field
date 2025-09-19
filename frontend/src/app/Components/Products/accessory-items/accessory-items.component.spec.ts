import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessoryItemsComponent } from './accessory-items.component';

describe('AccessoryItemsComponent', () => {
  let component: AccessoryItemsComponent;
  let fixture: ComponentFixture<AccessoryItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccessoryItemsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccessoryItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
