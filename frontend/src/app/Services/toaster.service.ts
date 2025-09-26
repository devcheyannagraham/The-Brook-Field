import { Injectable,signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {
  message = signal<{class:"success" | "error" | "info",message:string} | null>(null)

  constructor() { }
}
