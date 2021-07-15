import { DateTime } from 'luxon';

export function isEmpty(obj: object): boolean {
    for(var key in obj) {
        if(obj.hasOwnProperty(key))
            return false;
    }
    return true;
}

function ensureISO(timestamp: string) {
   return timestamp.replace(" ", "T")
}

export function formatDateTime(timestamp: any): string {
   var datetime;
   if (!timestamp) {
      return "--"
   } else if (typeof timestamp === "string") {
      datetime = DateTime.fromISO(ensureISO(timestamp))
   } else if (typeof timestamp === "number") {
      datetime = DateTime.fromMillis(timestamp)
   } else {
      return String(datetime)
   }
   return datetime.toFormat("yyyy-LL-dd HH:mm:ss ZZZ")
}

export function toEpochMillis(timestamp: any): number {
   if (!timestamp) {
      return 0
   } else if (typeof timestamp === "string") {
      return DateTime.fromISO(ensureISO(timestamp)).toMillis()
   } else if (typeof timestamp === "number") {
      if (Number.isNaN(timestamp)) {
         return 0
      }
      return timestamp
   } else {
      return 0
   }
}

export function durationToMillis(duration: string): number | undefined {
   if (duration.length === 0) {
      return undefined
   }
   duration = duration.replaceAll(',', ' ').trim().toLowerCase()
   let value = 0
   let failed = false
   const units = [ 's', 'm', 'h', 'd']
   const multiplier = [ 1000, 60_000, 3600_000, 86_400_000]
   units.forEach((u, i) => {
      if (duration.endsWith(u)) {
         duration = duration.substring(0, duration.length - 1).trimEnd()
         const lastSpace = duration.lastIndexOf(' ');
         const num = parseInt(duration.substring(lastSpace + 1))
         if (isNaN(num)) {
            // console.error("Cannot parse '" + u + "' from " + original)
            failed = true
            return
         }
         value += num * multiplier[i];
         duration = duration.substring(0, lastSpace).trimEnd()
      }
   });
   if (failed || duration.length > 0) {
      // console.error("Cannot parse '" + original + "', residuum is '" + duration + "'")
      return undefined
   }
   return value
}

export function millisToDuration(duration: number): string {
   let text = ""
   const units = [ 'd', 'h', 'm', 's']
   const multiplier = [ 86_400_000, 3600_000, 60_000, 1000 ]
   multiplier.forEach((m, i) => {
      if (duration >= m) {
         const num = Math.trunc(duration / m)
         text += num + units[i] + ' '
         duration -= num * m
      }
   })
   // ignore ms
   return text
}

export type PaginationInfo = {
   page: number,
   perPage: number,
   sort: string,
   direction: string,
}
