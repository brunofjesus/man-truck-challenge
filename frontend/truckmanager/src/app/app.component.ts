import { Component, OnInit } from '@angular/core';
import { TrucksService } from 'src/swagger';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'truckmanager';

  constructor(private trucksService : TrucksService){}

  ngOnInit(): void {
    this.trucksService.listTrucks().subscribe(truckList => {
      console.log(truckList);
    })
  }
}
