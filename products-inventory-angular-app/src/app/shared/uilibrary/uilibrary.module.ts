import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ToolbarModule} from 'primeng/toolbar';
import {ButtonModule} from 'primeng/button';
import {SplitButtonModule} from 'primeng/splitbutton';
import {TableModule} from 'primeng/table';
import {CheckboxModule} from 'primeng/checkbox';
import {TooltipModule} from 'primeng/tooltip';
import { ReactiveFormsModule } from '@angular/forms';
import {ToastModule} from 'primeng/toast';
import {MessageModule} from 'primeng/message';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ToolbarModule,
    ButtonModule,
    SplitButtonModule,
    TableModule,
    CheckboxModule,
    ReactiveFormsModule,
    TooltipModule,
    ToastModule,
    MessageModule
  ],
  exports: [
    ToolbarModule,
    ButtonModule,
    SplitButtonModule,
    TableModule,
    CheckboxModule,
    ReactiveFormsModule,
    TooltipModule,
    ToastModule,
    MessageModule
  ]
})
export class UilibraryModule { }
