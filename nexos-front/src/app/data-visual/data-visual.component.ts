// @ts-nocheck
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { DialogService } from '@fundamental-ngx/core/dialog';
import {DataService} from '../data.service';


@Component({
  selector: 'app-data-visual',
  templateUrl: './data-visual.component.html',
  styleUrls: ['./data-visual.component.css']
})
export class DataVisualComponent implements OnInit {
  // @ts-ignore
  tableRows: any[];
  // @ts-ignore
  displayedRows: any[];

  searchTerm = '';
  // @ts-ignore
  confirmationReason: string;
  // @ts-ignore
  createForm: FormGroup;

  editForm: FormGroup;

  loading = false;
  // @ts-ignore
  options: any[];
  // @ts-ignore
  userSelected: any;

  // tslint:disable-next-line:variable-name
  constructor(private _dialogService: DialogService, private _fb: FormBuilder, private _ds: DataService) {}

  ngOnInit(): void {
    this.loading = true;
    // @ts-ignore
    this._ds.getAllUsers().subscribe((data: any[]) => {
      this.options = data;
    });
    // @ts-ignore
    this._ds.getAllMercs().subscribe((data: any[]) => {
      this.loading = false;
      this.tableRows = data;
      this.resetSearch();
    });
    this.displayedRows = this.tableRows;

    this.createForm = this._fb.group({
      nameInput: new FormControl(''),
      amountInput: new FormControl('')
    });

    this.editForm = this._fb.group({
      nameInput: new FormControl(''),
      amountInput: new FormControl('')
    });
  }

  searchInputChanged(event: string): void {
    // @ts-ignore
    const filterRows = (row): boolean => {
      const keys = Object.keys(row);
      return !!keys.find(key => row[key].toLowerCase().includes(event.toLowerCase()));
    };

    if (event) {
      this.displayedRows = this.tableRows.filter(row => filterRows(row));
    } else {
      this.displayedRows = this.tableRows;
    }
  }

  resetSearch(): void {
    this.displayedRows = this.tableRows;
    this.searchTerm = '';
  }


  openDialog(dialog: TemplateRef<any>, id: any): void {
    const dialogRef = this._dialogService.open(dialog, { responsivePadding: true });

    dialogRef.afterClosed.subscribe(
      (result) => {
        console.log(result);
        this.confirmationReason = 'Dialog closed with result: ' + result;

        if ( result === 'edit')
        {
          this._ds.editMerc(id, this.editForm.get('nameInput').value, this.editForm.get('amountInput').value,
            new Date().toISOString().slice(0, 10),
            this.userSelected.id).subscribe();

        }else if (result === 'Save')
        {
          this._ds.addMerc(this.createForm.get('nameInput').value, this.createForm.get('amountInput').value,
            new Date().toISOString().slice(0, 10),
            this.userSelected.id).subscribe();
        }
        this._ds.getAllMercs().subscribe((data: any[]) => {
          this.loading = false;
          this.tableRows = data;
          this.resetSearch();
        });
        this.createForm.setValue({nameInput: '', amountInput: ''});
        this.editForm.setValue({nameInput: '', amountInput: ''});
      },
      (error) => {
        this.confirmationReason = 'Dialog dismissed with result: ' + error;
      }
    );
  }
}
