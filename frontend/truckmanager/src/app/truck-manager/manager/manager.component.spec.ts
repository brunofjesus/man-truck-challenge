import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerComponent } from './manager.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { GridComponent } from './grid/grid.component';
import { RouterTestingModule } from '@angular/router/testing';
import { StoreModule } from '@ngrx/store';
import { TruckReducer } from 'src/app/store/reducers/truck.reducer';
import { PropertiesReducer } from 'src/app/store/reducers/properties.reducer';

describe('ManagerComponent', () => {
  let component: ManagerComponent;
  let fixture: ComponentFixture<ManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerComponent, GridComponent],
      imports: [SharedModule, RouterTestingModule,
        StoreModule.forRoot({
          truck: TruckReducer,
          properties: PropertiesReducer
        })
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
