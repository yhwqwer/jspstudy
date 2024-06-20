package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActionForward {
  private String path;
  private boolean isRedirect;  // 기본값은 false 이므로 Forward
}
