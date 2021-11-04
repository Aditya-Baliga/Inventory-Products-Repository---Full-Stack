import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationPatternService {


  noWhitespaceOnly(): RegExp {
    return /[^]*[^\s][^]*/;
  }

  noHtmlTags(): RegExp {
    return /^(?![^]*(|&lt;|&#60;|&#x003C;)[^]*(>|&gt;|&#62;|&#x003E;))[^]*/;
  }

  noNonIntegral(): RegExp {
    return /^[0-9]+$/;
  }
}
